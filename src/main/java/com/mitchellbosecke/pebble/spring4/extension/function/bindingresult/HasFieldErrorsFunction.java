/*******************************************************************************
 * Copyright (c) 2013 by Mitchell Bösecke
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.mitchellbosecke.pebble.spring4.extension.function.bindingresult;

import com.mitchellbosecke.pebble.template.EvaluationContext;

import org.springframework.validation.BindingResult;

import java.util.Map;

/**
 * <p>
 * Function available to templates in Spring MVC applications in order to access
 * the BindingResult of a form
 * </p>
 *
 * @author Eric Bussieres
 */
public class HasFieldErrorsFunction extends BaseBindingResultFunction {

  public static final String FUNCTION_NAME = "hasFieldErrors";

  public HasFieldErrorsFunction() {
    super(PARAM_FORM_NAME, PARAM_FIELD_NAME);
  }

  @Override
  public Object execute(Map<String, Object> args) {
    String formName = (String) args.get(PARAM_FORM_NAME);
    String fieldName = (String) args.get(PARAM_FIELD_NAME);

    EvaluationContext context = (EvaluationContext) args.get("_context");
    BindingResult bindingResult = this.getBindingResult(formName, context);

    if (bindingResult != null) {
      if (fieldName == null) {
        return bindingResult.hasFieldErrors();
      } else {
        return bindingResult.hasFieldErrors(fieldName);
      }
    } else {
      return false;
    }
  }
}
