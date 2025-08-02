package com.shop.ShopApplication;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.shop.ShopApplication.Repository.JpaRepository")
@EnableElasticsearchRepositories(basePackages = "com.shop.ShopApplication.Repository.ElasticRepository")
public class ShopApplication {

	public static void main(String[] args) {
		Flyway flyway = Flyway.configure()
				.dataSource("jdbc:mariadb://localhost:3306/shop", "root", "")
				.load();
		flyway.repair();

		SpringApplication.run(ShopApplication.class, args);
	}
}
