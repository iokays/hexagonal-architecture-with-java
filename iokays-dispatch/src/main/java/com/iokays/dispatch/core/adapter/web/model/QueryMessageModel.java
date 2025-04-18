package com.iokays.dispatch.core.adapter.web.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class QueryMessageModel implements Serializable {

    private String category;

}
