package analysis.context;

import ait.CodeContext;
import analysis.MethodAnalyzer.MethodDescriber;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.model.declarations.ReferenceTypeDeclaration;

import java.util.List;

public class MethodOverrideNoAnnotation extends MethodOverride {

    public MethodOverrideNoAnnotation(ContextConfiguration cc) {
        super(cc);
    }

    @Override
    public boolean detect() throws Exception {

        boolean no_annotation = true;

        if(super.detect())
        {
            // first check local class
            ClassOrInterfaceDeclaration class4Analysis = Navigator.demandClassOrInterface(
                    _analyzer.getCompilationUnit(),
                    _analyzer.getQualifiedClassName());

            List<MethodDeclaration> md = class4Analysis.getMethods();

            for(MethodDeclaration item : md)
            {
                MethodDescriber methDescr = new MethodDescriber(item);

                if (methDescr.equals(_method))
                {
                    NodeList<AnnotationExpr> annotations = item.getAnnotations();
                    if (!annotations.isEmpty())
                    {
                        for(AnnotationExpr ea : annotations)
                        {
                            if(ea.getName().toString().contentEquals("Override"))
                            {
                                no_annotation = false;
                            }
                        }
                    }
                }
            }

            //  Check all ancestors for matching methods
            _rt.forEach( ancestor ->
            {
               // list from symbolsolver cannot easily get annotations from ast ...@todo
                //NodeList<AnnotationExpr> ae =  md.get(0).getAnnotations();
            });
        }

        return no_annotation;
    }

        @Override
    public CodeContext.CodeContextEnum getType() {
        return CodeContext.CodeContextEnum.MethodOverrideNoAnnotation;
    }
}
