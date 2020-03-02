package cz.sdm.exam.check.solution.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class FileContent {

    private String company;
    private String product;
    private LocalDateTime localDateTime;

}
