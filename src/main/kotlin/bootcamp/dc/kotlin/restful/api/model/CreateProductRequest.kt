package bootcamp.dc.kotlin.restful.api.model

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateProductRequest (

    @field:NotBlank //notblank only for string
    val id: String?,

    @field:NotBlank
    val name: String?,

    @field:NotNull //notnull for number
    @field:Min(value = 1)
    val price: Long?,

    @field:NotNull
    @field:Min(value = 0)
    val quantity: Int?,
)