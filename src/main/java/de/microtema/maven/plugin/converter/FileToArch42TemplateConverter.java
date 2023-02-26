package de.microtema.maven.plugin.converter;

import de.microtema.maven.plugin.model.Arch42Template;
import de.microtema.model.converter.MetaConverter;

public class FileToArch42TemplateConverter implements MetaConverter<Arch42Template, String, String> {

    @Override
    public void update(Arch42Template dest, String orig) {

        update(dest, orig, null);
    }

    @Override
    public void update(Arch42Template dest, String orig, String meta) {

        String indexAsString = orig.split("_")[0];

        String[] tokens = indexAsString.split("/");

        if (tokens.length > 1) {
            indexAsString = tokens[tokens.length - 1].split("_")[0];
        }

        dest.setIndex(Integer.parseInt(indexAsString));
        dest.setFileName(orig);
        dest.setTitle(meta);
    }
}
