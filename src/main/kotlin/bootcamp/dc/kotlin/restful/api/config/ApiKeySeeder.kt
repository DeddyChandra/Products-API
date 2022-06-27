package bootcamp.dc.kotlin.restful.api.config

import bootcamp.dc.kotlin.restful.api.entity.ApiKey
import bootcamp.dc.kotlin.restful.api.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

// ApplicationRunner is a class that will be executed when the spring framework is running (every first time)
@Component
class ApiKeySeeder(val apiKeyRepository: ApiKeyRepository): ApplicationRunner {

    val apiKey = "SECRET"

    override fun run(args: ApplicationArguments?) {
        if(!apiKeyRepository.existsById(apiKey)){
            val apiKeyEntity = ApiKey(id= apiKey)
            apiKeyRepository.save(apiKeyEntity)
        }
    }

}