package club.bagedate.o2o.mapper;

import club.bagedate.o2o.entity.ShopCategory;
import club.bagedate.o2o.entity.ShopCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopCategoryMapper {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    long countByExample(ShopCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    int deleteByExample(ShopCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    int deleteByPrimaryKey(Integer shopcategoryid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    int insert(ShopCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    int insertSelective(ShopCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    List<ShopCategory> selectByExample(ShopCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    ShopCategory selectByPrimaryKey(Integer shopcategoryid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    int updateByExampleSelective(@Param("record") ShopCategory record, @Param("example") ShopCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    int updateByExample(@Param("record") ShopCategory record, @Param("example") ShopCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    int updateByPrimaryKeySelective(ShopCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopcategory
     *
     * @mbg.generated Tue May 14 10:48:08 CST 2019
     */
    int updateByPrimaryKey(ShopCategory record);
}