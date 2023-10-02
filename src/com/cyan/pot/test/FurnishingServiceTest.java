package com.cyan.pot.test;

import com.cyan.pot.domain.Furnishing;
import com.cyan.pot.domain.Page;
import com.cyan.pot.service.FurnishingService;
import com.cyan.pot.service.impl.FurnishingServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public class FurnishingServiceTest {
    FurnishingService furnishingService = new FurnishingServiceImpl();

    @Test
    public void testQueryFurnishings() {
        List<Furnishing> furnishings = furnishingService.queryFurnishings();
        for (Furnishing furnishing : furnishings) {
            System.out.println(furnishing.getPrice());
            System.out.println(furnishing.getImgPath());
            System.out.println(furnishing.getId());
            System.out.println(furnishing.getName());
            System.out.println(furnishing.getEnterprise());
            System.out.println(furnishing.getSales());
            System.out.println(furnishing.getStock());
        }
    }

    @Test
    public void testAddFurnishing() {
        Furnishing furnishing = new Furnishing(null, "朱漆垂香木卷轴书架", "飞云商会(璃月)", new BigDecimal(25000), 25, 1, "resources/images/product-image/2.jpg");

        furnishingService.addFurnishing(furnishing);
    }

    @Test
    public void testDeleteFurnishing() {
        System.out.println(furnishingService.deleteFurnishing(3));
    }

    @Test
    public void testGetPage() {
        /** 先向表中添加一些测试的记录 */
        //Page<Furnishing> page = furnishingService.getPage(3, 3);

        //System.out.println(page);

    }

    @Test void testGetPageByName() {
        Page<Furnishing> baishe = furnishingService.getPageByName(1, 4, "摆设");
        System.out.println(baishe);
    }

    @Test
    public void testAlterCartItem() {
        //none
    }
}
