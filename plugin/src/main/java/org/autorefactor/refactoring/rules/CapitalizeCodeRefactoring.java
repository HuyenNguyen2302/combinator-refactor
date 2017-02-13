/**
 * 
 */
package org.autorefactor.refactoring.rules;

import static org.autorefactor.refactoring.ASTHelper.DO_NOT_VISIT_SUBTREE;
import static org.autorefactor.refactoring.ASTHelper.VISIT_SUBTREE;
import static org.autorefactor.refactoring.SourceLocation.getEndPosition;

import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.autorefactor.refactoring.Refactorings;
import org.autorefactor.refactoring.SourceLocation;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * @author huyennguyen
 *
 */
public class CapitalizeCodeRefactoring extends AbstractRefactoringRule {

	/* (non-Javadoc)
	 * @see org.autorefactor.refactoring.RefactoringRule#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Capitalize selected code";
	}

	/* (non-Javadoc)
	 * @see org.autorefactor.refactoring.RefactoringRule#getName()
	 */
	@Override
	public String getName() {
		return "Capitalize Code";
	}

    @Override
    public boolean visit(CompilationUnit node) {
        final String source = this.ctx.getSource(node);
        if (source.length() == 0) {
            // empty file, bail out
            return VISIT_SUBTREE;
        }

        final Refactorings r = this.ctx.getRefactorings();

        r.replace(SourceLocation.fromPositions(0, source.length()), capitalizeCode(source));
        
        return VISIT_SUBTREE;
    }
    
    private String capitalizeCode(String code) {
    	return code.toUpperCase();
    }
}
