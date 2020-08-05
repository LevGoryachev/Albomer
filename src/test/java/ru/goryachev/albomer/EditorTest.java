package ru.goryachev.albomer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class EditorTest {

    public String savedName;
    public String savedDesc;
    public String savedField;
    public Boolean expected;

    public EditorTest(String savedName, String savedDesc, String savedField, Boolean expected) {
        this.savedName = savedName;
        this.savedDesc = savedDesc;
        this.savedField = savedField;
        this.expected = expected;
    }

    @Test
    public void testEditor() {
        Editor editor = new Editor(savedName, savedDesc, savedField);

        Boolean actual = editor.getName() == savedName && editor.getDesc() == savedDesc && editor.getTxtInField() == savedField;

        Assert.assertNotNull(editor);
        Assert.assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static List<Object[]> testData() {
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[] {"AlbomOne", "Description one", "Fields list one", true});
        params.add(new Object[] {" ", " ", " ", true});
        params.add(new Object[] {null, null, null, true});
    return params;
    }
}