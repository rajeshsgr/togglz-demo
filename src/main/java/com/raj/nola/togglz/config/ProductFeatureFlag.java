package com.raj.nola.togglz.config;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;

public enum ProductFeatureFlag implements Feature {


    @EnabledByDefault
    @Label("Additional information about a book")
    ADDITIONAL_INFORMATION,

    @EnabledByDefault
    @Label("Price during festive period")
    FESTIVE_DISCOUNT,


}
