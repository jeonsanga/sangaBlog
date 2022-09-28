package sample.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserVO {
	
    private Integer userId; /* userId */
    private String userName; /* userName */
    private String userEmail; /* userEmail */
    private String userPassword; /* userPassword */
    private String userCheck; /* userCheck */

}
