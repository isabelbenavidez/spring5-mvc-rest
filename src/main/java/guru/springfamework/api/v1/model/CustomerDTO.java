package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @ApiModelProperty(value="Este es el Primer nombre", required = true)
    //private Long id;
    private String firstName;

    @ApiModelProperty(required = true)
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl; //No está en el entity, por eso se agrega la anotación @JsonProperty("customer_url)

}
