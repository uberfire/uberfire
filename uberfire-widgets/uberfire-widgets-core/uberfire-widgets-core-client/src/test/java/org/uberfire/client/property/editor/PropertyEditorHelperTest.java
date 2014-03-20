package org.uberfire.client.property.editor;

import java.util.ArrayList;

import com.github.gwtbootstrap.client.ui.Accordion;
import com.github.gwtbootstrap.client.ui.AccordionGroup;
import com.github.gwtbootstrap.client.ui.event.ShowHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.uberfire.client.property.editor.api.PropertyEditorCategory;
import org.uberfire.client.property.editor.api.PropertyEditorEvent;
import org.uberfire.client.property.editor.api.PropertyEditorFieldInfo;
import org.uberfire.client.property.editor.api.fields.PropertyEditorType;
import org.uberfire.client.property.editor.widgets.PropertyEditorErrorWidget;
import org.uberfire.client.property.editor.widgets.PropertyEditorItemLabel;
import org.uberfire.client.property.editor.widgets.PropertyEditorItemWidget;
import org.uberfire.client.property.editor.widgets.PropertyEditorItemsWidget;
import org.uberfire.client.property.editor.widgets.PropertyEditorTextBox;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class PropertyEditorHelperTest {

    @Test(expected = PropertyEditorHelper.NullEventException.class)
    public void validateNullEventTest() {
        PropertyEditorHelper.validade( null );
    }

    @Test(expected = PropertyEditorHelper.NoPropertiesException.class)
    public void validateEventWithNoPropertiesTest() {
        PropertyEditorEvent event = new PropertyEditorEvent( "id", new ArrayList<PropertyEditorCategory>() );
        PropertyEditorHelper.validade( event );
    }

    @Test
    public void validateEventTest() {
        ArrayList<PropertyEditorCategory> properties = new ArrayList<PropertyEditorCategory>();
        properties.add( new PropertyEditorCategory( "Category" ) );
        PropertyEditorEvent event = new PropertyEditorEvent( "id", properties );
        assertTrue( PropertyEditorHelper.validade( event ) );
    }

    @Test
    public void isAMatchOfEmptyFilterTest() {
        PropertyEditorFieldInfo field = new PropertyEditorFieldInfo();
        assertTrue( PropertyEditorHelper.isAMatchOfFilter( "", field ) );
    }

    @Test
    public void isAMatchOfFilterTest() {
        PropertyEditorFieldInfo field = new PropertyEditorFieldInfo( "label", PropertyEditorType.TEXT );
        assertTrue( PropertyEditorHelper.isAMatchOfFilter( "l", field ) );
        assertTrue( PropertyEditorHelper.isAMatchOfFilter( "label", field ) );
        assertTrue( PropertyEditorHelper.isAMatchOfFilter( "LABEL", field ) );
        assertTrue( PropertyEditorHelper.isAMatchOfFilter( "abel", field ) );
        assertFalse( PropertyEditorHelper.isAMatchOfFilter( "LABELL", field ) );
        assertFalse( PropertyEditorHelper.isAMatchOfFilter( "LASBELL", field ) );
        assertFalse( PropertyEditorHelper.isAMatchOfFilter( "p", field ) );
    }

    @Test
    public void createLabelTest() {
        PropertyEditorFieldInfo field = new PropertyEditorFieldInfo( "label", PropertyEditorType.TEXT );
        PropertyEditorItemLabel label = PropertyEditorHelper.createLabel( field );
        verify( label ).setText( "label" );
    }

    @Test
    public void createFieldTest() {
        final Widget itemWidget = GWT.create( PropertyEditorTextBox.class );

        PropertyEditorFieldInfo field = new PropertyEditorFieldInfo( "label", PropertyEditorType.TEXT ) {
            @Override
            public Widget getWidget() {

                return itemWidget;
            }
        };
        PropertyEditorItemsWidget parent = GWT.create( PropertyEditorItemsWidget.class );

        PropertyEditorItemWidget fieldCreated = PropertyEditorHelper.createField( field, parent );
        verify( fieldCreated, atLeastOnce() ).add( any( Widget.class ) );
    }

    @Test
    public void createErrorHandlingInfraStructureTest() {

        PropertyEditorTextBox widget = GWT.create( PropertyEditorTextBox.class );
        PropertyEditorItemWidget itemWidget = GWT.create( PropertyEditorItemWidget.class );
        PropertyEditorItemsWidget parent = GWT.create( PropertyEditorItemsWidget.class );
        PropertyEditorErrorWidget errorWidget = GWT.create( PropertyEditorErrorWidget.class );

        PropertyEditorHelper.createErrorHandlingInfraStructure( parent, itemWidget, errorWidget, widget );
        verify( widget ).setErrorWidget( errorWidget );
        verify( widget ).setParent( parent );
        verify( itemWidget ).add( widget );

    }

    @Test
    public void createItemsWidgetTest() {

        final Widget itemWidget = GWT.create( PropertyEditorTextBox.class );

        PropertyEditorFieldInfo field = new PropertyEditorFieldInfo( "label", PropertyEditorType.TEXT ) {
            @Override
            public Widget getWidget() {

                return itemWidget;
            }
        };

        PropertyEditorItemsWidget items = PropertyEditorHelper.createItemsWidget( field );
        verify( items, atLeastOnce() ).add( any( PropertyEditorTextBox.class ) );
        verify( items, atLeastOnce() ).add( any( PropertyEditorItemLabel.class ) );
    }

    @Test
    public void createAccordionGroupTest() {
        PropertyEditorWidget propertyEditorWidget = GWT.create( PropertyEditorWidget.class );
        PropertyEditorCategory category = new PropertyEditorCategory( "Category" );
        AccordionGroup accordionGroup = PropertyEditorHelper.createAccordionGroup( propertyEditorWidget, category );
        verify( accordionGroup ).setHeading( "Category" );
        verify( accordionGroup ).addShowHandler( any( ShowHandler.class ) );
    }

    @Test
    public void createOpenAccordionGroupTest() {
        PropertyEditorWidget propertyEditorWidget = GWT.create( PropertyEditorWidget.class );
        when( propertyEditorWidget.getLastOpenAccordionGroupTitle() ).thenReturn( "Category" );
        PropertyEditorCategory category = new PropertyEditorCategory( "Category" );
        AccordionGroup accordionGroup = PropertyEditorHelper.createAccordionGroup( propertyEditorWidget, category );
        verify( accordionGroup ).setHeading( "Category" );
        verify( accordionGroup ).addShowHandler( any( ShowHandler.class ) );
        verify( accordionGroup ).setDefaultOpen( true );
    }

    @Test
    public void createCategoryWithNoFields() {
        PropertyEditorWidget propertyEditorWidget = GWT.create( PropertyEditorWidget.class );
        Accordion propertyMenu = GWT.create( Accordion.class );

        PropertyEditorCategory category = new PropertyEditorCategory( "1" );
        PropertyEditorHelper.createCategory( propertyEditorWidget, propertyMenu, category, "" );

        verify( propertyMenu, never() ).add( any( Widget.class ) );

    }

    @Test
    public void createCategoryWithFields() {
        final Widget itemWidget = GWT.create( PropertyEditorTextBox.class );

        PropertyEditorFieldInfo field = new PropertyEditorFieldInfo( "label", PropertyEditorType.TEXT ) {
            @Override
            public Widget getWidget() {

                return itemWidget;
            }
        };

        PropertyEditorWidget propertyEditorWidget = GWT.create( PropertyEditorWidget.class );
        Accordion propertyMenu = GWT.create( Accordion.class );

        PropertyEditorCategory category = new PropertyEditorCategory( "1" );
        category.withField( field );
        category.withField( field );
        PropertyEditorHelper.createCategory( propertyEditorWidget, propertyMenu, category, "" );

        verify( propertyMenu, times( 1 ) ).add( any( Widget.class ) );

    }

}
