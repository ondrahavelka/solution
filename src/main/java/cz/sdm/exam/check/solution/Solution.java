package cz.sdm.exam.check.solution;

import cz.sdm.exam.check.solution.model.FileContent;
import cz.sdm.exam.check.solution.service.FileService;
import cz.sdm.exam.check.solution.util.CounterMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
public class Solution implements CommandLineRunner {

    @Autowired
    private FileService fileService;

    private CounterMap<FileContent, Integer> counterMap = new CounterMap<>();

    @Override
    public void run(String... args) throws Exception {
        List<FileContent> allContent = fileService.parseArgumentsAndFindFiles(args);
        allContent.forEach(e -> counterMap.put(e));
        processContent(allContent);

    }

    private void processContent(List<FileContent> content) {
        counterMap.keySet().forEach(companyKey -> counterMap.get(companyKey).sort(Comparator.comparing(FileContent::getLocalDateTime)));
        print(content);
      }

    private void print(List<FileContent> content){
        storeToDb();
        content.forEach(fContent -> log.info(fContent.getCompany() + "|" + (counterMap.getFormattedIndex(fContent)) + "|" + fContent.getProduct()));
    }

    private void storeToDb() {

    }



}
