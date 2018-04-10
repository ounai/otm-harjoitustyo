package fi.ounai.nyssetulee.api;

import fi.ounai.nyssetulee.domain.Stop;

public interface StopAPI {
    Stop[] getStops(String name);
}
