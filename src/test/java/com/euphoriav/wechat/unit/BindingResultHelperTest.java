package com.euphoriav.wechat.unit;

import com.euphoriav.wechat.helper.BindingResultHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BindingResultHelperTest {

    @Mock
    private BindingResult bindingResult;
    private final BindingResultHelper bindingResultHelper = new BindingResultHelper();

    private final static FieldError LOGIN_ERROR = new FieldError("registerRequest", "login", "login is already taken");
    private final static FieldError NAME_ERROR = new FieldError("registerRequest", "name", "name must be not blank");
    private final static List<FieldError> ERRORS = List.of(LOGIN_ERROR, NAME_ERROR);

    @Test
    public void saveErrorsToModelTest() {
        Model model = new BindingAwareModelMap();
        when(bindingResult.getFieldErrors()).thenReturn(ERRORS);

        bindingResultHelper.saveErrorsToModel(bindingResult, model);

        assertTrue(model.containsAttribute("errors"));
        @SuppressWarnings("unchecked")
        Map<String, String> errors = (Map<String, String>) model.getAttribute("errors");
        assertEquals(errors.get(LOGIN_ERROR.getField()), LOGIN_ERROR.getDefaultMessage());
        assertEquals(errors.get(NAME_ERROR.getField()), NAME_ERROR.getDefaultMessage());
    }
}
