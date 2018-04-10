package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Route;

public interface RouteAPI {
    Route[] getRoutes(String name);
    Route[] getRoutes(String name, String modes);
}
