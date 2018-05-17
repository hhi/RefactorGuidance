package analysis.context;

import ait.CodeContext;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/**
 * Determines which specific contexts are present in a piece of code in a generic way.
 * by evaluating all given context detectors.
 */
public class ContextAnalyzer {

    List<IContextDetector> _contextDetectors = new ArrayList<IContextDetector>();
    EnumSet<CodeContext.CodeContextEnum> _detectedSet = EnumSet.noneOf(CodeContext.CodeContextEnum.class);

    public void setContextDetectors(List<IContextDetector> detectors) {
        this._contextDetectors = detectors;
    }

    public EnumSet<CodeContext.CodeContextEnum> getDetectedContextSet()
    {
        return _detectedSet;
    }

    public void run()
    {
        for(IContextDetector detector : _contextDetectors)
            try {
                if (detector.detect()) {
                    _detectedSet.add(detector.getType());
                    Map<String, String> parameters = detector.getParameterMap();
                    
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public Map<String,String> getParameterMap() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
