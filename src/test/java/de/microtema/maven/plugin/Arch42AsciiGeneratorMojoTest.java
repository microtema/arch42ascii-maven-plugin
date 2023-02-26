package de.microtema.maven.plugin;

import org.apache.commons.io.FileUtils;
import org.apache.maven.project.MavenProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class Arch42AsciiGeneratorMojoTest {

    @InjectMocks
    Arch42AsciiGeneratorMojo sut;

    @Mock
    MavenProject project;

    @BeforeEach
    void setUp() {

        sut.project = project;
    }

    @Test
    void executeOnNonUpdateFalse() throws Exception {

        when(project.getName()).thenReturn("Microtema Reporting Service");
        when(project.getProperties()).thenReturn(new Properties());

        sut.outputFile = "target/README.adoc";

        sut.execute();

        File file = new File(sut.outputFile);

        String answer = FileUtils.readFileToString(file, "UTF-8");
        assertEquals("= Microtema Reporting Service\n" +
                "\n" +
                ":imagesdir: ./docs\n" +
                "\n" +
                ":numbered:\n" +
                "\n" +
                "== Introduction and Goals\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "== Constraints\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "== Context and Scope\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "== Solution Strategy\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "== Building Block View\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "== Runtime View\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "== Deployment View\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "== Crosscutting Concepts\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "== Architectural Decisions\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "== Quality Requirements\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "== Risks and Technical Debt\n" +
                "\n" +
                "Inherited from bootstrap\n" +
                "\n" +
                "include::docs/12_glossary.adoc[]\n" +
                "\n", answer);
    }
}
