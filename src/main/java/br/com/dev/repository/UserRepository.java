package br.com.dev.repository;

import br.com.dev.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by FelipeWendt on 01/11/17.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
