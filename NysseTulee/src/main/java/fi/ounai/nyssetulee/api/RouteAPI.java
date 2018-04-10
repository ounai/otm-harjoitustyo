package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Route;

public interface RouteAPI {
    Route[] getRoutes(String name) throws Exception;
    Route[] getRoutes(String name, String modes) throws Exception;
}
