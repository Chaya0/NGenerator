package com.generator.util;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.rewrite.ImportRewrite;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ImportOrganizer {

	 public static void organizeImports(String filePath) throws IOException, CoreException {
	        String source = new String(Files.readAllBytes(Paths.get(filePath)));

	        // Parse the source code
	        ASTParser parser = ASTParser.newParser(AST.JLS2); // Use the appropriate JLS version
	        parser.setSource(source.toCharArray());
	        parser.setKind(ASTParser.K_COMPILATION_UNIT);
	        parser.setResolveBindings(true);
	        parser.setBindingsRecovery(true);

	        // Create AST from source code
	        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

	        // Create import rewrite
	        ImportRewrite importRewrite = ImportRewrite.create(cu, true);

	        // Collect all used imports
	        cu.accept(new ASTVisitor() {
	            @Override
	            public boolean visit(SimpleType node) {
	                importRewrite.addImport(node.resolveBinding());
	                return super.visit(node);
	            }

	            @Override
	            public boolean visit(QualifiedType node) {
	                importRewrite.addImport(node.resolveBinding());
	                return super.visit(node);
	            }

	            @Override
	            public boolean visit(NameQualifiedType node) {
	                importRewrite.addImport(node.resolveBinding());
	                return super.visit(node);
	            }

	            @Override
	            public boolean visit(MethodInvocation node) {
	                if (node.resolveMethodBinding() != null) {
	                    importRewrite.addImport(node.resolveMethodBinding().getDeclaringClass());
	                }
	                return super.visit(node);
	            }
	        });

	        // Apply the import rewrite edits to the source
	        TextEdit edits = importRewrite.rewriteImports(null);
	        Document document = new Document(source);
	        try {
	            edits.apply(document);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        // Write the organized imports back to the file
	        Files.write(Paths.get(filePath), document.get().getBytes());
	    }
	
}
