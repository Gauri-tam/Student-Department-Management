package com.StudDept.confoguration;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ImageConfig {


    @Bean
    public Cloudinary getCloudinary(){
        Map map = new HashMap();

        map.put("Cloud Name", "drprig6up");
        map.put("api-key", "465868717587657");
        map.put("api-secrete", "O9XpGzgL2m4bMhcNilCaPNu2dnI");
        map.put("secure", true);

        return new Cloudinary(map);
    }

}
