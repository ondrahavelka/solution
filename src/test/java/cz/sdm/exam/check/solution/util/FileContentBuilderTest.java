package cz.sdm.exam.check.solution.util;

import cz.sdm.exam.check.solution.model.FileContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FileContentBuilder.class})
@TestPropertySource("/application.properties")
class FileContentBuilderTest {

    @Autowired
    private FileContentBuilder fileContentBuilder;

    @Test
    void build() throws Exception {
        FileContent builded = fileContentBuilder.build("payment weekly, Netflix/603603603, 2013-09-05 14:08:15");
        assertEquals("Netflix", builded.getCompany());
        assertEquals("payment weekly", builded.getProduct());
        LocalDateTime localDateTime = LocalDateTime.of(2013, 9, 5, 14, 8, 15);
        assertEquals(localDateTime, builded.getLocalDateTime());
    }

    @ParameterizedTest
    @MethodSource
    void testErrorInputs(String input) {
        assertThrows(Exception.class, () -> {
            fileContentBuilder.build(input);
        });
    }

    static Stream<String> testErrorInputs() {
        return Stream.of(
                "        payment weekly Netflix/603603603 2013-09-0514:08:15",
                "game Of Thrones; Apple-603123456, 2015-06-20 15:13:22",
                "payment yearly Netflix / 604222333 2013-09-05 14:07:13",
                " Office 365,, 2015-07-23 08:03:02",
                ",,",
                "payment weekly, Apple / 603999888,",
                "application Any.DO, Microsoft / 604123456, 2015-09-01 12:00:0",
                "new subscription , Netflix / 602456987, 2013.09.06 15:40:22",
                "/*-+-.§),,,,,(%, Netflix / 602666999, 2016-02-13 13:33:50",
                "too many long text describing a product which should be rather banned actually so long I dont know what to write here without proper grammar commas and such so exception is thrown for the length of this text, Netflix / 602666999, 2016-02-13 13:33:50",
                "payment, NetflixHboAppleMicrosoftTmobileO2Wodafone / 602666999, 2016-02-13 13:33:50",
                "O2TV, HBO%GO\", Netflix /603605506, 2016-01-02 15:15:01"
                );
    }
}