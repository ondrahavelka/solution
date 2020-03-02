package cz.sdp.exam.check.solution.service;

import cz.sdp.exam.check.solution.exceptions.InitialLoadException;
import cz.sdp.exam.check.solution.model.FileContent;
import cz.sdp.exam.check.solution.util.FileContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Service
public class FileService {

    @Autowired
    private FileContentBuilder builder;
    /**
     * Process given arguments and prepare files to load
     *
     * @param args
     * @return
     * @throws InitialLoadException
     */
    public List<FileContent> parseArgumentsAndFindFiles(String ... args) throws InitialLoadException {
        if (args == null || args.length == 0) {
            throw new InitialLoadException("+++ Divide By Cucumber Error. Please Reinstall Universe And Reboot. +++");
        }
        List<FileContent> filesToReturn = new ArrayList<>();
        for (String fileLocation : args) {
            filesToReturn.addAll(getContent(fileLocation));
        }
        return filesToReturn;
    }

    private List<FileContent> getContent(String filePath) throws InitialLoadException {
        List<FileContent> content = new ArrayList<>();
        AtomicReference<Exception> exception = new AtomicReference<>();
        try (Stream<String> s = Files.lines(Paths.get(filePath))) {
            s.forEach(e -> {
                try {
                    content.add(builder.build(e));
                } catch (Exception ex) {
                    exception.set(ex);
                }
            });
            if(exception.get() != null){
                throw exception.get();
            }
        } catch (Exception ex) {
            throw new InitialLoadException("+++ Divide By Cucumber Error. Please Reinstall Universe And Reboot. +++" + ex.getMessage());
        }
        return content;
    }
}
