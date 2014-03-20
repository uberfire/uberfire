package org.uberfire.client.property.editor.api.fields;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import org.uberfire.client.property.editor.api.PropertyEditorChangeEvent;
import org.uberfire.client.property.editor.api.PropertyEditorFieldInfo;
import org.uberfire.client.property.editor.widgets.PropertyEditorCheckBox;

@Dependent
public class BooleanField extends AbstractField {

    @Inject
    Event<PropertyEditorChangeEvent> propertyEditorChangeEventEvent;

    @Override
    public Widget widget( final PropertyEditorFieldInfo property ) {
        final PropertyEditorCheckBox checkBox = GWT.create( PropertyEditorCheckBox.class );

        checkBox.addValueChangeHandler( new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange( ValueChangeEvent<Boolean> event ) {

                if ( validate( property, checkBox.getValue().toString() ) ) {
                    checkBox.clearOldValidationErrors();
                    property.setCurrentStringValue( checkBox.getValue().toString() );
                    propertyEditorChangeEventEvent.fire( new PropertyEditorChangeEvent( property, checkBox.getValue().toString() ) );
                } else {
                    checkBox.setValidationError( getValidatorErrorMessage( property, checkBox.getValue().toString() ) );
                    checkBox.setValue( new Boolean( property.getCurrentStringValue() ) );
                }
            }
        } );

        return checkBox;
    }

}
