package com.wdy.product.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wdy.product.model.entity.TbGoods;
import com.wdy.product.model.entity.TbGoodsDesc;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodDTD implements Serializable {

    private static final long serialVersionUID = -629258389710064860L;


    private TbGoods goods;

    private TbGoodsDesc goodsDesc;

  /*  private List<TbItem> itemList;*/
    private String itemList;
}
