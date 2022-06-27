package bootcamp.dc.kotlin.restful.api.auth

import bootcamp.dc.kotlin.restful.api.error.UnauthorizedException
import bootcamp.dc.kotlin.restful.api.repository.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository): WebRequestInterceptor{
    // function will be called when controller haven't executed
    override fun preHandle(request: WebRequest) {
        val apiKey = request.getHeader("X-Api-Key")
        if(apiKey == null){
            throw UnauthorizedException()
        }

        if(!apiKeyRepository.existsById(apiKey)){
            throw UnauthorizedException()
        }

        //valid
    }
    //function will be called when controller have executed
    override fun postHandle(request: WebRequest, model: ModelMap?) {
        //nothing
    }
    //function will be called after everything is done including render view
    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        //nothing
    }

}