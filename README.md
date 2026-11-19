# Flight Wall — Android tablet

This project builds an Android APK for TCL TAB 8 Gen 2, landscape, centered at ZIP 33173 with 10-mile radius.

## Build from a tablet using GitHub
1. Create a new public GitHub repository called `flightwall-android` (do not initialize with README).
2. Upload the **contents** of this folder to the repository, preserving directories, including `.github/workflows/build.yml`. GitHub mobile upload can be cumbersome for folders; using the GitHub web editor for each file is an alternative.
3. Open the repository **Actions** tab, choose **Build Android APK**, and run the workflow (or let the push trigger it).
4. Once the build succeeds, open the run and download the `FlightWall-APK` artifact ZIP. Extract it on the tablet, then install `app-debug.apk` from Files. Allow installation from that source if prompted.

The APK uses airplanes.live and planespotters.net public endpoints. Their policies, availability and rate limits may change. It requires Wi-Fi. This is a proof-of-concept, not a guaranteed 24/7 tracking solution. The radar is a local range plot, not a tiled geographic map. Aircraft images are only shown when a match exists.
