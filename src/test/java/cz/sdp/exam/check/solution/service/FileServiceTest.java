package cz.sdp.exam.check.solution.service;

import cz.sdp.exam.check.solution.exceptions.InitialLoadException;
import cz.sdp.exam.check.solution.model.FileContent;
import cz.sdp.exam.check.solution.util.FileContentBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FileService.class, FileContentBuilder.class})
@TestPropertySource("/application.properties")
class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    void parseArgumentsAndFindFiles() throws InitialLoadException {
        // should not throw exception
        List<FileContent> fileContents = fileService.parseArgumentsAndFindFiles(new String[]{"src/test/resources/input.txt"});
        assertNotNull(fileContents);
        assertNotEquals(0, fileContents.size());
    }

    @Test
    void parseArgumentsAndFindFiles_emptyContentFile() throws InitialLoadException {
        List<FileContent> fileContents = fileService.parseArgumentsAndFindFiles(new String[]{"src/test/resources/empty_input.txt"});
        assertNotNull(fileContents);
        assertEquals(0, fileContents.size());
    }

    @Test
    void parseArgumentsAndFindFiles_shouldThrowException() throws InitialLoadException {
        // should not throw exception
        assertThrows(InitialLoadException.class, () -> {
            fileService.parseArgumentsAndFindFiles("");
        });
        assertThrows(InitialLoadException.class, () -> {
            fileService.parseArgumentsAndFindFiles("src/test/resources/incorrect_input.txt");
        });
    }
}