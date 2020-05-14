package plus.ldl.day08elasticsearchmybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import plus.ldl.day08elasticsearchmybatis.domain.Goods;

import java.util.List;

/**
 * @author ldl.plus
 * @date 2020年05月11日  15:40
 */
@Mapper
public interface GoodsMapper {

    /**
     * 查询所有
     *
     * @return List<Goods>
     */
    @Select("select id, title, price, stock, saleNum, createTime, categoryName, brandName, spec from goods")
    List<Goods> findAll();
}
