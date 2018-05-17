package analysis.context;

import ait.CodeContext;
import analysis.MethodAnalyzer.ClassMethodFinder;

import java.util.Map;

public class MethodMultipleDeclarations implements IContextDetector {

    ClassMethodFinder _analyzer = null;
    String _methodName = null;

    public MethodMultipleDeclarations(ClassMethodFinder cmf, String methodName) {
        this._analyzer = cmf;
        this._methodName = methodName;
    }

    public MethodMultipleDeclarations(ContextConfiguration cc) {
        this._analyzer = cc.getCMFAnalyzer();
        this._methodName = cc.getMethodName();
    }

    public boolean detect() throws Exception {
        boolean result = false;

        if(_analyzer != null)
        {
            result = _analyzer.isMethodDefinedInSuperClass(_methodName) ||
                     _analyzer.isMethodDeclaredFirstTimeInInterface(_methodName);
        }
        else
        {
            throw(new Exception("Analyzer = null"));
        }

        return result;
    }

    @Override
    public Map<String,String> getParameterMap() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CodeContext.CodeContextEnum getType() {
        return CodeContext.CodeContextEnum.MethodMultipleDeclarations;
    }

}
