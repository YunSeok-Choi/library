package assignment.library.domain.user.repository;

import assignment.library.domain.user.dto.response.QUserInfoResponse;
import assignment.library.domain.user.dto.response.UserInfoResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static assignment.library.domain.user.entity.QUser.user;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    public List<UserInfoResponse> getUsersInfo(Long userId) {

        return queryFactory
                .select(new QUserInfoResponse(user.userId, user.userName, user.userEmail, user.userPassword))
                .from(user)
                .where(userIdEq(userId))
                .fetch();
    }

    private BooleanExpression userIdEq(Long userId) {
        return isEmpty(userId) ? null : user.userId.eq(userId);
    }

}
