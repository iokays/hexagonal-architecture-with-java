package com.iokays.sample.domain.customer;

import com.iokays.common.domain.mongodb.AbstractCodePropertyValueConverter;

public class CustomerCodeConverter extends AbstractCodePropertyValueConverter<CustomerCode> {

    @Override
    protected CustomerCode create(String id) {
        return null != id ? new CustomerCode(id) : null;
    }
}
