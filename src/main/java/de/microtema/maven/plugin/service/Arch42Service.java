package de.microtema.maven.plugin.service;

import de.microtema.maven.plugin.converter.FileToArch42TemplateConverter;
import de.microtema.maven.plugin.model.Arch42Template;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Arch42Service {

    private final FileToArch42TemplateConverter fileToArch42TemplateConverter;

    public List<Arch42Template> getAvailableTemplates(String docsDir) {

        File dir = new File(docsDir);

        if (!dir.exists()) {
            return Collections.emptyList();
        }

        String[] list = dir.list((dir1, name) -> name.endsWith(".adoc"));

        return Stream.of(Objects.requireNonNull(list)).map(it -> fileToArch42TemplateConverter.convert(dir.getName() + "/" + it)).collect(Collectors.toList());
    }

    public List<Arch42Template> getTemplates() {

        AtomicInteger index = new AtomicInteger(1);

        return Stream.of(
                        "Introduction and Goals",
                        "Constraints",
                        "Context and Scope",
                        "Solution Strategy",
                        "Building Block View",
                        "Runtime View",
                        "Deployment View",
                        "Crosscutting Concepts",
                        "Architectural Decisions",
                        "Quality Requirements",
                        "Risks and Technical Debt",
                        "Glossary"
                ).map(it -> fileToArch42TemplateConverter.convert(index.getAndIncrement() + "_", it))
                .collect(Collectors.toList());
    }


}
