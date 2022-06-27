package bootcamp.dc.kotlin.restful.api.repository

import bootcamp.dc.kotlin.restful.api.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository: JpaRepository<ApiKey, String> {
}