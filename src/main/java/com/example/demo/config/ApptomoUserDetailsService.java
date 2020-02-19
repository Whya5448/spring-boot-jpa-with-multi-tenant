package com.example.demo.config;

import com.example.demo.Utils;
import com.example.demo.account.AccountRepository;
import java.util.NoSuchElementException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ApptomoUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public ApptomoUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] split = Utils.getRequest().getRequestURI().split("/");
        String stage = split[split.length - 1];
        boolean matches = stage.matches("(?:DEV|TST|PRD):\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}");
        if (!matches) {
            throw new UsernameNotFoundException("데이터베이스 정보가 없습니다.");
        }
        Utils.setDatabase(stage);

        return accountRepository.findById(username).orElseThrow(NoSuchElementException::new);
    }
}
