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
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            bodyTypeNames.add(bodyType.getName().toLowerCase());
        }
        return bodyTypeNames;
    }

    private List<String> getMakes() {
        List<Meta> makes = (List<Meta>) metaRepository.findAllByType("make");
        List<String> makeNames = new ArrayList<>();
        for (Meta make : makes) {
            makeNames.add(make.getName().toLowerCase());
        }
        return makeNames;
    }


    public Double milesToMeters(Optional<Integer> miles) {
        if (miles.isPresent()) {
            return miles.get() * 1609.34;
        }
        return 250 * 1609.34;
    }

    public AbstractMap.SimpleEntry<List<String>, List<String>> parseParams(ArrayList<String>params) {
        List<String> bodyTypes = getBodyTypes();
        List<String> makes = getMakes();

        List<String> type_params = new ArrayList<>();
        List<String> makes_params = new ArrayList<>();

        for (String param : params) {
           if(bodyTypes.contains(param.toLowerCase())) {
               type_params.add(param.toLowerCase());
           } else if(makes.contains(param.toLowerCase())) {
                makes_params.add(param.toLowerCase());
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
                                       Optional<Integer> postcode,
                                       Optional<Integer> radius,
                                       Optional<Double> priceMin,
                                       Optional<Double> priceMax,
                                       Optional<String> condition_code,
                                       Optional<String> model_code,
                                       Pageable pageRequest) {


        List<String> bodyTypes = getBodyTypes();

        AbstractMap.SimpleEntry<List<String>, List<String>> tuple
                = parseParams(params);

        List<String> makes = tuple.getKey();
        List<String> types = tuple.getValue();


        Query query = new Query();

        for(String param :makes.size() > 0 ? makes : getMakes()) {

            Criteria criteria = Criteria.where("make").is(param.toUpperCase());

            if(types.size() > 0) {
                for(String type : types) {
                    Criteria typeCriteria = Criteria.where("body").is(type);
                    criteria = criteria.andOperator(typeCriteria);
                }
            }

            if(model_code.isPresent()) {
                String[] codes = model_code.get().split("_");

                for(String code: codes) {
                    Criteria modelCriteria = Criteria.where("model").orOperator(Criteria.where("model").is(code));
                    criteria = criteria.andOperator(modelCriteria);
                }

            }

            if(condition_code.isPresent()) {
                String[] codes = condition_code.get().split("_");

                for(String code : codes) {
                    if(code.equals("isNew")) {
                        Criteria conditionCriteria = Criteria.where("isNew").is(true);
                        criteria = criteria.orOperator(conditionCriteria);
                    }
                    else if (code.equals("isOemcpo")) {
                        Criteria conditionCriteria = Criteria.where("isOemcpo").is(true);
                        criteria = criteria.orOperator(conditionCriteria);
                    }
                    else if (code.equals("isCpo")) {
                        Criteria conditionCriteria = Criteria.where("isCpo").is(true);
                        criteria = criteria.orOperator(conditionCriteria);
                    }
                    else if (code.equals("isCertified")) {
                        Criteria conditionCriteria = Criteria.where("isCertified").is(true);
                        criteria = criteria.orOperator(conditionCriteria);
                    }
                    else if (code.equals("isUsed")) {
                        Criteria conditionCriteria = Criteria.where("isUsed").is(true);
                        criteria = criteria.orOperator(conditionCriteria);
                    }

                }
            }

            if(color_code.isPresent()) {
                String[] codes = color_code.get().split("_");

                for(String code: codes) {
                    Criteria colorCriteria = Criteria.where("color").orOperator(Criteria.where("color").is(code));
                    criteria = criteria.orOperator(colorCriteria);
                }

            }

            if(body_code.isPresent()) {
                String[] codes = body_code.get().split("_");
                for(String code: codes) {
                    Criteria bodyCriteria = Criteria.where("body").orOperator(Criteria.where("body").is(code));
                    criteria = criteria.orOperator(bodyCriteria);
                }

            }

            if(drivetrain_code.isPresent()) {
                String[] codes = drivetrain_code.get().split("_");

                for(String code: codes) {
                    Criteria drivetrainCriteria = Criteria.where("drivetrain").orOperator(Criteria.where("drivetrain").is(code));
                    criteria = criteria.orOperator(drivetrainCriteria);
                }

            }

            if(fuel_code.isPresent()) {
                String[] codes = fuel_code.get().split("_");
                for(String code: codes) {
                    Criteria fuelCriteria = Criteria.where("fuel").orOperator(Criteria.where("fuel").is(code));
                    criteria = criteria.orOperator(fuelCriteria);
                }

            }

            if(transmission_code.isPresent()) {
                String[] codes = transmission_code.get().split("_");

                for(String code: codes) {
                    Criteria transmissionCriteria = Criteria.where("transmission").orOperator(Criteria.where("transmission").is(code));
                }

            }

            if(start_year.isPresent() && end_year.isPresent()) {
                Criteria yearCriteria = Criteria.where("year").gte(start_year.get()).lte(end_year.get());
                criteria = criteria.andOperator(yearCriteria);
            } else if (start_year.isPresent()) {
                Criteria yearCriteria = Criteria.where("year").gte(start_year.get());
                criteria = criteria.andOperator(yearCriteria);
            } else if (end_year.isPresent()) {
                Criteria yearCriteria = Criteria.where("year").lte(end_year.get());
                criteria = criteria.andOperator(yearCriteria);
            }

            if(mileage.isPresent()) {
                Criteria mileageCriteria = Criteria.where("mileage").lte(mileage.get());
                criteria = criteria.andOperator(mileageCriteria);
            }

            if(postcode.isPresent()) {
                try {
                    Optional<Location> location = locationRepository.findByPostcode(postcode.get());
                    if(location.isPresent()) {
                        //double lat = location.get().getLat();
                        //double lon = location.get().getLon();
                       // double radiusInMiles = radius.isPresent() ? radius.get() : 100;
                       // double radiusInKm = radiusInMiles * 1.60934;
                       // Criteria locationCriteria = Criteria.where("location").nearSphere(new Point(lon, lat)).maxDistance(radiusInKm);
                       // query.addCriteria(locationCriteria);
                    } else {
                        GeoApiContext context = new GeoApiContext.Builder()
                                .apiKey(googleApiKey)
                                .build();
                        GeocodingResult[] results =  GeocodingApi.geocode(context,
                                String.valueOf(postcode)).await();
                        Geometry geometry = results[0].geometry;

                        Location newLocation = new Location();
                        newLocation.setPostcode(postcode.get());
                        Coordinate coordinate = new Coordinate(geometry.location.lng, geometry.location.lat);
                        Point comparisonPoint = factory.createPoint(coordinate);
                        newLocation.setPoint(comparisonPoint);

                        Location save = locationRepository.save(newLocation);

                       // Predicate dealerPredicate = SpatialPredicates.distanceWithin(criteriaBuilder, root.get("dealer").get("location"), save.getPoint(), milesToMeters(radius));
                       // subPredicates.add(dealerPredicate);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if(priceMin.isPresent() && priceMax.isPresent()) {
                Criteria priceCriteria = Criteria.where("price").gte(priceMin.get()).lte(priceMax.get());
                //criteria = criteria.andOperator(priceCriteria);
            } else if (priceMin.isPresent()) {
                Criteria priceCriteria = Criteria.where("price").gte(priceMin.get());
                //criteria = criteria.andOperator(priceCriteria);
            } else if (priceMax.isPresent()) {
                Criteria priceCriteria = Criteria.where("price").lte(priceMax.get());
                //criteria = criteria.andOperator(priceCriteria);
            }

            query.addCriteria(criteria);
        }

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
               // query.with(new Sort(Sort.Direction.ASC, property));
            } else {
               // query.with(new Sort(Sort.Direction.DESC, property));
            }
        }

        Long count = mongoTemplate.count(query, Auto.class, "auto");
        query.with(pageRequest);

        List<Auto> result =
                   mongoTemplate.find(query, Auto.class, "auto");

        Page<Auto> page = new PageImpl<>(result, pageRequest, count);
        return page;
    }
}
