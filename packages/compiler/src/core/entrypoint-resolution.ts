import { doIO, loadFile } from "../utils/io.js";
import { resolveTspMain } from "../utils/misc.js";
import { DiagnosticHandler } from "./diagnostics.js";
import { resolvePath } from "./path-utils.js";
import { CompilerHost } from "./types.js";

/**
 * Resolve the path to the main file
 * @param path path to the entrypoint of the program. Can be the main.tsp, folder containing main.tsp or a project/library root.
 * @returns Absolute path to the entrypoint.
 */
export async function resolveTypeSpecEntrypoint(
  host: CompilerHost,
  path: string,
  reportDiagnostic: DiagnosticHandler,
): Promise<string | undefined> {
  const resolvedPath = resolvePath(path);
  const mainStat = await doIO(host.stat, resolvedPath, reportDiagnostic);
  if (!mainStat) {
    return undefined;
  }

  if (mainStat.isDirectory()) {
    return resolveTypeSpecEntrypointForDir(host, resolvedPath, reportDiagnostic);
  } else {
    return resolvedPath;
  }
}

export async function resolveTypeSpecEntrypointForDir(
  host: CompilerHost,
  dir: string,
  reportDiagnostic: DiagnosticHandler,
): Promise<string> {
  const pkgJsonPath = resolvePath(dir, "package.json");
  const [pkg] = await loadFile(host, pkgJsonPath, JSON.parse, reportDiagnostic, {
    allowFileNotFound: true,
  });
  const tspMain = resolveTspMain(pkg);
  if (tspMain !== undefined) {
    return resolvePath(dir, tspMain);
  }

  return resolvePath(dir, "main.tsp");
}
