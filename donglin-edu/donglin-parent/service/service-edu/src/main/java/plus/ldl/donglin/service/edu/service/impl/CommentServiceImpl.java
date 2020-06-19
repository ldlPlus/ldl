package plus.ldl.donglin.service.edu.service.impl;

import plus.ldl.donglin.service.edu.entity.Comment;
import plus.ldl.donglin.service.edu.mapper.CommentMapper;
import plus.ldl.donglin.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author ldl.plus
 * @since 2020-06-20
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
