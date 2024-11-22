package com.iokays.sample.domain.customer;

import com.iokays.common.domain.mongodb.AbstractIdPropertyValueConverter;

public class CustomerIdConverter extends AbstractIdPropertyValueConverter<CustomerId> {

    @Override
    protected CustomerId create(String id) {
        return null != id ? new CustomerId(id) : null;
    }
}
