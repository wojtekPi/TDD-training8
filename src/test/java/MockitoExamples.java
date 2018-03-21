import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class MockitoExamples {

    @Test
    public void exampleOfSpy() throws Exception {
        List<String> stringList = new ArrayList<>();
        List<String> stringListSpy = Mockito.spy(stringList);
        Mockito.when(stringListSpy.size())
                .thenReturn(100);
        stringListSpy.add("Ala");

        assertThat(stringListSpy.get(0)).isEqualTo("Ala");
        assertThat(stringListSpy.size()).isEqualTo(100);
    }

    @Test
    public void shouldReturnSevenForAnyArgument() throws Exception {
        List<String> stringList = mock(List.class);
        Mockito.when(stringList.get(any(Integer.class))).thenReturn("7");


        assertThat(stringList.get(0)).isEqualTo("7");
        assertThat(stringList.get(1)).isEqualTo("7");
        assertThat(stringList.get(100)).isEqualTo("7");
        assertThat(stringList.get(100)).isEqualTo("7");

        Mockito.verify(stringList, times(4)).get(any(Integer.class));
    }

    @Test
    public void shouldAddElement() throws Exception {
        List<String> listMock = mock(List.class);

        listMock.add(null);

        Mockito.verify(listMock, times(1)).add(nullable(String.class));
    }
}
