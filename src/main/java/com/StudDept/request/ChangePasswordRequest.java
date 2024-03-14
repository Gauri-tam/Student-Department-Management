package com.StudDept.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
/***
 * old password  == match (bCrypt)
 *
 * change password == ********
 * conform password == ********
 *
 *  change password == conform password (match /not match)
 *
 *  if (match){
 *      save in database
 *  }
 *  else{
 *      throw new exception(----------)
 *  }
 */
    private String currentPass;
    private String newPass;
    private String conformPass;
}
