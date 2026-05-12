# Progreard rules for SaveUp
--keepAttributes *Annotation*
--keepclassmembers class * {
    @android.room.* <fields>;
    @android.room.* <methods>;
}