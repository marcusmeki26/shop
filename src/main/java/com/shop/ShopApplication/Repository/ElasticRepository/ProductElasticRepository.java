package com.shop.ShopApplication.Repository.ElasticRepository;

import com.shop.ShopApplication.Entity.Document.ProductDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductElasticRepository extends ElasticsearchRepository<ProductDocument, String> {
    @Query("""
            {
                "multi_match": {
                    "query": "?0",
                    "fields": ["productName^3", "categoryName^2"],
                    "fuzziness": "2",
                    "prefix_length": 3
                }
            }
            """)
    List<ProductDocument> findByProductNameFuzzy(String productName);

    @Query("""
            {
                "bool": {
                    "filter": [
                        { "term": { "categoryName.keyword" : "?1" }}
                    ],
                    "must": [
                        { "match": { "productName": { "query": "?0", "fuzziness": "2", "prefix_length": 3 }}}
                    ]
                }
            }
            """)
    List<ProductDocument> findByCategoryNameAndProductNameFuzzy(String keyword, String categoryName);
}
