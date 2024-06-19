import { Routes } from '@angular/router';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { UserDashboardComponent } from './user/user-dashboard/user-dashboard.component';
import { HealhServiceComponent } from './user/healh-service/healh-service.component';
import { UserComponent } from './user/user/user.component';
import { AdminComponent } from './admin/admin/admin.component';
import { DoctorListComponent } from './admin/doctor/doctor-list/doctor-list.component';
import { LoginComponent } from './admin/login/login.component';
import { AdminGuard } from './auth/guards/admin-guard/admin-guard.guard';
import { NoGuard } from './auth/guards/no-guard/no-guard.guard';
import { ListRoomComponent } from './admin/room/list-room/list-room.component';
import { ListSuppliesComponent } from './admin/item/supply/list-supplies/list-supplies.component';
import { DoctorComponent } from './doctor/doctor/doctor.component';
import { DoctorLoginComponent } from './doctor/doctor-login/doctor-login.component';
import { ListMedicationComponent } from './admin/item/medication/list-medication/list-medication.component';
import { ListShiftComponent } from './admin/shift/list-shift/list-shift.component';
import { DoctorDashboardComponent } from './doctor/doctor-dashboard/doctor-dashboard.component';
import { DoctorScheduleComponent } from './doctor/doctor-schedule/doctor-schedule.component';
import { DoctorRegisterScheduleComponent } from './doctor/doctor-register-schedule/doctor-register-schedule.component';
import { ListPatientComponent } from './admin/patient/list-patient/list-patient.component';
import { DoctorListPatientComponent } from './doctor/doctor-list-patient/doctor-list-patient.component';
import { DoctorDiagnosticComponent } from './doctor/doctor-diagnostic/doctor-diagnostic.component';
import { DoctorTestResultComponent } from './doctor/doctor-test-result/doctor-test-result.component';
import { RegistryScheduleManagementComponent } from './admin/registry-schedule-management/registry-schedule-management.component';
import { AppointmentComponent } from './user/appointment/appointment.component';
import { MedicalInformationComponent } from './user/medical-information/medical-information.component';
import { ListAppointmentComponent } from './admin/appointment/list-appointment/list-appointment.component';
import { DoctorGuard } from './auth/guards/doctor-guard/doctor-guard.guard';

export const routes: Routes = [
    // admin 
    { 
        path: 'admin', component: AdminComponent,
        children: [
            { path: 'login', component: LoginComponent, canActivate: [NoGuard] },
            { path: 'dashboard', component: DashboardComponent, canActivate: [AdminGuard] },
            // xử lý về bác sỹ
            { path: 'list-doctor', component: DoctorListComponent, canActivate: [AdminGuard] },
            // xử lý về bệnh nhân
            { path: 'list-patient/:status', component: ListPatientComponent, canActivate: [AdminGuard] },
            // xử lý về phòng
            { path: 'list-room', component: ListRoomComponent, canActivate: [AdminGuard] },
            // xử lý về vật tư
            { path: 'list-supply', component: ListSuppliesComponent, canActivate: [AdminGuard] },
            // xử lý về thuốc
            { path: 'list-medication', component: ListMedicationComponent, canActivate: [AdminGuard] },
            // xử lý về ca làm việc
            { path: 'list-shift', component: ListShiftComponent, canActivate: [AdminGuard] },
            { path: 'registry-schedule-management', component: RegistryScheduleManagementComponent, canActivate: [AdminGuard] },
            // xử lý về lịch hẹn
            { path: 'list-appointment', component: ListAppointmentComponent, canActivate: [AdminGuard] },
        ]
    },

    // doctor
    {
        path: 'doctor', component: DoctorComponent,
        children: [
            { path: 'login', component: DoctorLoginComponent, canActivate: [NoGuard] },
            { path: 'dashboard', component: DoctorDashboardComponent, canActivate: [DoctorGuard] },
            { path: 'work-schedule', component: DoctorScheduleComponent, canActivate: [DoctorGuard] },
            { path: 'register-schedule', component: DoctorRegisterScheduleComponent, canActivate: [DoctorGuard] },
            { path: 'list-patient/:status', component: DoctorListPatientComponent, canActivate: [DoctorGuard] },
            { path: 'diagnostic/:id', component: DoctorDiagnosticComponent, canActivate: [DoctorGuard] },
            { path: 'list-test', component: DoctorTestResultComponent, canActivate: [DoctorGuard] },
        ]
    },

    // patient
    {
        path: '', component: UserComponent,
        children: [
            { path: 'dashboard', component: UserDashboardComponent },
            { path: 'health-service', component: HealhServiceComponent },
            { path: 'appointment', component: AppointmentComponent },
            { path: 'medical-information', component: MedicalInformationComponent },
        ]
    }
];
