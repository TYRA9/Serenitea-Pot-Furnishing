package com.cyan.pot.test;

import com.cyan.pot.dao.FurnishingDAO;
import com.cyan.pot.dao.impl.FurnishingDAOImpl;
import com.cyan.pot.domain.Furnishing;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author : Cyan_RA9
 * @version : 21.0
 */
public class FurnishingDAOTest {
    FurnishingDAO furnishingDAO = new FurnishingDAOImpl();
    @Test
    public void testQueryFurnishings() {
        List<Furnishing> furnishings = furnishingDAO.queryFurnishings();
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
    public void testGetRecordSumByName() {
        int sum = furnishingDAO.getRecordSumByName("垂香木");
        System.out.println("sum = " + sum);
    }
}
