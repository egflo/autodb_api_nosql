package com.autodb_api.dao;

import com.autodb_api.models.Auto;
import com.autodb_api.models.Location;
import com.autodb_api.models.Meta;
import com.autodb_api.repository.LocationRepository;
import com.autodb_api.repository.MetaRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.mongodb.client.model.geojson.Point;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

@Component
public class AutoDao {
    @Value("${googleApiKey}")
    private String googleApiKey;

    @Autowired
    MetaRepository metaRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    // WGS-84 SRID
    private GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);

    public AutoDao() {


    }

    /**
    public AutoDao(EntityManager entityManager,
                   BodyTypeRepository bodyTypeRepository,
                   MakeRepository makeRepository,
                   LocationRepository locationRepository) {
        this.entityManager = entityManager;
        this.bodyTypeRepository = bodyTypeRepository;
        this.locationRepository = locationRepository;
        this.makeRepository = makeRepository;

    }
    */

    private List<String> getBodyTypes() {
        List<Meta> bodyTypes = (List<Meta>) metaRepository.findAllByType("body");
        List<String> bodyTypeNames = new ArrayList<>();
        for (Meta bodyType : bodyTypes) {
            bodyTypeNames.add(bodyType.getName());
        }
        return bodyTypeNames;
    }

    private List<String> getMakes() {
        List<Meta> makes = (List<Meta>) metaRepository.findAllByType("make");
        List<String> makeNames = new ArrayList<>();
        for (Meta make : makes) {
            makeNames.add(make.getName());
        }
        return makeNames;
    }

    public Double milesToMeters(Optional<Integer> miles) {
        if (miles.isPresent()) {
            return miles.get() * 1609.34;
        }
        return 250 * 1609.34;
    }

    public List<Criteria> createCriteria(String field, Optional<String> values) {

        String[] codes = values.get().split("_");

        List<Criteria> criteria = new ArrayList<>();
        for (String value : codes) {
            criteria.add(Criteria.where(field).is(value));
        }
        return criteria;
    }

    public AbstractMap.SimpleEntry<List<String>, List<String>> parseParams(ArrayList<String> params) {



        List<String> bodyTypes = getBodyTypes();
        List<String> makes = getMakes();

        List<String> type_params = new ArrayList<>();
        List<String> makes_params = new ArrayList<>();

        for (String param : params) {

            System.out.println("param: " + param);

           if(bodyTypes.contains(param)) {
               type_params.add(param);
           } else if(makes.contains(param)) {
                makes_params.add(param);
           }
           else {
               makes_params.add("all");
           }
        }

        AbstractMap.SimpleEntry<List<String>, List<String>> params_map =
                new AbstractMap.SimpleEntry<>(makes_params, type_params);

        return params_map;
    }

    public Page<Auto> findAutoByParams(ArrayList<String> params,
                                       Optional<String> color_code,
                                       Optional<String> body_code,
                                       Optional<String> drivetrain_code,
                                       Optional<String> fuel_code,
                                       Optional<String> transmission_code,
                                       Optional<Integer> start_year,
                                       Optional<Integer> end_year,
                                       Optional<Double> mileage,
                                       Optional<String> postcode,
                                       Optional<Integer> radius,
                                       Optional<Double> priceMin,
                                       Optional<Double> priceMax,
                                       Optional<String> condition_code,
                                       Optional<String> model_code,
                                       Pageable pageRequest) {


        AbstractMap.SimpleEntry<List<String>, List<String>> tuple
                = parseParams(params);

        List<String> makes = tuple.getKey();
        List<String> types = tuple.getValue();


        Query query = new Query();

        List<Criteria> finalCriteria = new ArrayList<>();

        for(String param : makes.size() > 0 ? makes : getMakes()) {

            Criteria criteria = Criteria.where("make").is(param);

            if (param.contains("all")) {
                criteria = Criteria.where("make").exists(true);
            }

            List<Criteria> criteriaList = new ArrayList<>();

            if(types.size() > 0) {
                List<Criteria> type_criteria = new ArrayList<>();
                for (String value : types) {
                    type_criteria.add(Criteria.where("body.body_type").is(value));
                }
                criteriaList.add(new Criteria().orOperator(type_criteria.toArray(new Criteria[type_criteria.size()])));
            }

            if(model_code.isPresent()) {
                List<Criteria> orCriteria = createCriteria("model", model_code);
                Criteria orBody = new Criteria().orOperator(orCriteria.toArray(new Criteria[orCriteria.size()]));
                criteriaList.add(orBody);
            }

            if(condition_code.isPresent()) {
                String[] codes = condition_code.get().split("_");

                for(String code : codes) {
                    if(code.equals("isNew")) {
                        Criteria conditionCriteria = Criteria.where("isNew").is(true);
                        criteriaList.add(conditionCriteria);
                    }
                    else if (code.equals("isOemcpo")) {
                        Criteria conditionCriteria = Criteria.where("isOemcpo").is(true);
                        criteriaList.add(conditionCriteria);
                    }
                    else if (code.equals("isCpo")) {
                        Criteria conditionCriteria = Criteria.where("isCpo").is(true);
                        criteriaList.add(conditionCriteria);
                    }
                    else if (code.equals("isCertified")) {
                        Criteria conditionCriteria = Criteria.where("isCertified").is(true);
                        criteriaList.add(conditionCriteria);
                    }
                    else if (code.equals("isUsed")) {
                        Criteria conditionCriteria = Criteria.where("isUsed").is(true);
                        criteriaList.add(conditionCriteria);
                    }

                }
            }

            if(color_code.isPresent()) {
                List<Criteria> orCriteria = createCriteria("listing_color", color_code);
                Criteria orBody = new Criteria().orOperator(orCriteria.toArray(new Criteria[orCriteria.size()]));
                criteriaList.add(orBody);
            }

            if(body_code.isPresent()) {
                List<Criteria> orCriteria = createCriteria("body.body_type", body_code);
                Criteria orBody = new Criteria().orOperator(orCriteria.toArray(new Criteria[orCriteria.size()]));
                criteriaList.add(orBody);
            }

            if(drivetrain_code.isPresent()) {
                List<Criteria> orCriteria = createCriteria("drivetrain.wheel_system_display", drivetrain_code);
                Criteria orBody = new Criteria().orOperator(orCriteria.toArray(new Criteria[orCriteria.size()]));
                criteriaList.add(orBody);
            }

            if(fuel_code.isPresent()) {
                List<Criteria> orCriteria = createCriteria("fuel_type", fuel_code);
                Criteria orBody = new Criteria().orOperator(orCriteria.toArray(new Criteria[orCriteria.size()]));
                criteriaList.add(orBody);
            }

            if(transmission_code.isPresent()) {

                List<Criteria> orCriteria = createCriteria("transmission.transmission_display", transmission_code);
                Criteria orBody = new Criteria().orOperator(orCriteria.toArray(new Criteria[orCriteria.size()]));
                criteriaList.add(orBody);
            }

            if(start_year.isPresent() && end_year.isPresent()) {
                Criteria yearCriteria = Criteria.where("year").gte(start_year.get()).lte(end_year.get());
                criteriaList.add(yearCriteria);
            } else if (start_year.isPresent()) {
                Criteria yearCriteria = Criteria.where("year").gte(start_year.get());
                criteriaList.add(yearCriteria);
            } else if (end_year.isPresent()) {
                Criteria yearCriteria = Criteria.where("year").lte(end_year.get());
                criteriaList.add(yearCriteria);
            }

            if(mileage.isPresent()) {
                Criteria mileageCriteria = Criteria.where("mileage").lte(mileage.get());
                criteriaList.add(mileageCriteria);
            }

            if(postcode.isPresent()) {
                try {
                    Optional<Location> location = locationRepository.findByPostcode(postcode.get());
                    if(location.isPresent()) {
                        GeoJsonPoint point = location.get().getPoint();
                        double lat = point.getY();
                        double lon = point.getX();

                        // double radiusInMiles = radius.isPresent() ? radius.get() : 100;
                       // double radiusInKm = radiusInMiles * 1.60934;
                       // Criteria locationCriteria = Criteria.where("location").nearSphere(new Point(lon, lat)).maxDistance(radiusInKm);
                       // query.addCriteria(locationCriteria);
                        double radiusInMiles = milesToMeters(radius);

                        Criteria locationCriteria =
                                Criteria.where("dealer.point").nearSphere(point).maxDistance(radiusInMiles);
                        criteriaList.add(locationCriteria);
                    } else {

                        GeoApiContext context = new GeoApiContext.Builder()
                                .apiKey(googleApiKey)
                                .build();
                        GeocodingResult[] results =  GeocodingApi.geocode(context,
                                String.valueOf(postcode)).await();
                        Geometry geometry = results[0].geometry;

                        Location newLocation = new Location();
                        newLocation.setPostcode(postcode.get());
                        //Coordinate coordinate = new Coordinate(geometry.location.lng, geometry.location.lat);
                        //Point comparisonPoint = factory.createPoint(coordinate);
                        //Point comparisonPoint = factory.createPoint(coordinate);
                        //newLocation.setPoint(comparisonPoint);

                        GeoJsonPoint point = new GeoJsonPoint(geometry.location.lng, geometry.location.lat);
                        newLocation.setPoint(point);

                        Location save = locationRepository.save(newLocation);

                        double radiusInMiles = milesToMeters(radius);
                        Criteria locationCriteria =
                                Criteria.where("dealer.point").nearSphere(point).maxDistance(radiusInMiles);
                        criteriaList.add(locationCriteria);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if(priceMin.isPresent() && priceMax.isPresent()) {
                Criteria priceCriteria = Criteria.where("price").gte(priceMin.get()).lte(priceMax.get());
                criteriaList.add(priceCriteria);
            } else if (priceMin.isPresent()) {
                Criteria priceCriteria = Criteria.where("price").gte(priceMin.get());
                criteriaList.add(priceCriteria);
            } else if (priceMax.isPresent()) {
                Criteria priceCriteria = Criteria.where("price").lte(priceMax.get());
                criteriaList.add(priceCriteria);
            }


            if(criteriaList.size() > 0) {
                Criteria[] criteriaArray = new Criteria[criteriaList.size()];
                criteriaList.toArray(criteriaArray);
                criteria.andOperator(criteriaArray);
            }

            finalCriteria.add(criteria);
        }

        query.addCriteria(new Criteria().orOperator(finalCriteria.toArray(new Criteria[finalCriteria.size()])));

        Sort sort = pageRequest.getSort();

        for (Sort.Order order : sort)
        {
            String property = order.getProperty();
            String direction = order.getDirection().name();
            System.out.println("Property: " + order.getProperty());
            System.out.println("Direction: " + order.getDirection());

            if(property.toLowerCase().contains("optional")){
                continue;
            }

            if(direction.equals("ASC")) {
                //query.with(new Sort(Sort.Direction.ASC, property));
            } else {
               //query.with(new Sort(Sort.Direction.DESC, property));
            }
        }

        //Long count = mongoTemplate.count(query, Auto.class, "auto");
        query.with(pageRequest);

        List<Auto> result =
                   mongoTemplate.find(query, Auto.class, "auto");

        Page<Auto> page = new PageImpl<>(result, pageRequest, 10);
        return page;
    }
}
