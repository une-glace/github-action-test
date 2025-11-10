/**
 * @kind problem
 * @id demo/java/localvar-hardcoded-password
 * @name Hardcoded password (LocalVariableDecl)
 * @description Detect local variable declarations that contain a hardcoded password.
 * @tags security
 */

import java


from LocalVariableDecl v, RefType t, StringLiteral lit
where
  v.getInitializer() = lit and
  v.getType() = t and
  t.hasQualifiedName("java.lang", "String") and
  v.getName().regexpMatch("(?i)password")
select v, "Hardcoded password: " + lit.getValue()


