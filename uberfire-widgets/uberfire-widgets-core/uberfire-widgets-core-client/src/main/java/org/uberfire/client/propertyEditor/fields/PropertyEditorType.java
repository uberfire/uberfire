package org.uberfire.client.propertyEditor.fields;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.aria.client.Property;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.ioc.client.container.IOC;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.uberfire.client.propertyEditor.api.PropertyEditorCategory;
import org.uberfire.client.propertyEditor.api.PropertyEditorFieldInfo;
import org.uberfire.client.propertyEditor.fields.validators.IntegerValidator;
import org.uberfire.client.propertyEditor.fields.validators.PropertyFieldValidator;

public enum PropertyEditorType {

    TEXT {
        @Override
        public Widget widget( PropertyEditorFieldInfo property ) {
            return getWidget( property, TextField.class );
        }
        @Override
        public boolean isType( Class<?> type ) {
            return ( type.equals( String.class ) );
        }

    }, BOOLEAN {
        @Override
        public Widget widget( PropertyEditorFieldInfo property ) {
            return getWidget( property, BooleanField.class );
        }

        @Override
        public boolean isType( Class<?> type ) {
            return ( type.equals( Boolean.class ) || ( type.toString().equalsIgnoreCase( "boolean" ) ) );
        }
    }, NATURAL_NUMBER {
        @Override
        public Widget widget( PropertyEditorFieldInfo property ) {
            return TEXT.widget( property );
        }

        @Override
        public List<PropertyFieldValidator> getValidators() {
            ArrayList validators = new ArrayList();
            validators.add( new IntegerValidator() );
            return validators;
        }

        @Override
        public boolean isType( Class<?> type ) {
            return isInteger( type ) || isLong( type );
        }

        private boolean isLong( Class<?> type ) {
            return ( type.equals( Long.class ) || ( type.toString().equalsIgnoreCase( "long" ) ) );
        }

        private boolean isInteger( Class<?> type ) {
            return ( type.equals( Integer.class ) || ( type.toString().equalsIgnoreCase( "int" ) ) );
        }
    }, COMBO {
        @Override
        public Widget widget( PropertyEditorFieldInfo property ) {
            return getWidget( property, ComboField.class );
        }

        @Override
        public boolean isType( Class<?> type ) {
            return type.isEnum();
        }
    }, SECRET_TEXT {
        @Override
        public Widget widget( PropertyEditorFieldInfo property ) {
            return getWidget( property, SecretTextField.class );
        }
    };

    private static Widget getWidget( PropertyEditorFieldInfo property,
                                     Class fieldtype ) {
        IOCBeanDef iocBeanDef = IOC.getBeanManager().lookupBean( fieldtype );
        AbstractField field = (AbstractField) iocBeanDef.getInstance();
        return field.widget( property );
    }

    public abstract Widget widget( PropertyEditorFieldInfo property );

    public boolean isType( Class<?> type ) {
        return false;
    }

    public List<PropertyFieldValidator> getValidators() {
        return new ArrayList();
    }

    public static PropertyEditorType getFromType( Class<?> type ) {
        for ( PropertyEditorType candidate : PropertyEditorType.values() ) {
            if ( candidate.isType( type ) ) {
                return candidate;
            }
        }
        return null;
    }
}
