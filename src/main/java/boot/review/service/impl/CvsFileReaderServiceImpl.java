package boot.review.service.impl;

import boot.review.exceptions.FileIsNotReadable;
import boot.review.service.FileReaderService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CvsFileReaderServiceImpl implements FileReaderService {
    @Override
    public List<String> read(String path) {
        File file = new File(path);
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.readLine();
        } catch (IOException e) {
            throw new FileIsNotReadable("We cannot read file");
        }
        return bufferedReader.lines().collect(Collectors.toList());
    }
}
