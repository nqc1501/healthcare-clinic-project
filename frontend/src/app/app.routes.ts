import { Routes } from '@angular/router';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { UserDashboardComponent } from './user/user-dashboard/user-dashboard.component';
import { HealhServiceComponent } from './user/healh-service/healh-service.component';
import { UserComponent } from './user/user/user.component';
import { AdminComponent } from './admin/admin/admin.component';
import { CreateDoctorComponent } from './admin/doctor/create-doctor/create-doctor.component';
import { DoctorListComponent } from './admin/doctor/doctor-list/doctor-list.component';
import { LoginComponent } from './admin/login/login.component';
import { AdminGuard } from './auth/guards/admin-guard/admin-guard.guard';
import { NoGuard } from './auth/guards/no-guard/no-guard.guard';
import { ListRoomComponent } from './admin/room/list-room/list-room.component';
import { AddNewSupplyComponent } from './admin/item/supply/add-new-supply/add-new-supply.component';
import { ListSuppliesComponent } from './admin/item/supply/list-supplies/list-supplies.component';
import { AddNewSpecialistComponent } from './admin/specialist/add-new-specialist/add-new-specialist.component';
import { DoctorComponent } from './doctor/doctor/doctor.component';
import { DoctorLoginComponent } from './doctor/doctor-login/doctor-login.component';
import { ListSpecialistsComponent } from './admin/specialist/list-specialists/list-specialists.component';
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

export const routes: Routes = [
    // admin 
    { 
        path: 'admin', component: AdminComponent,
        children: [
            { path: 'login', component: LoginComponent, canActivate: [NoGuard] },
            { path: 'dashboard', component: DashboardComponent, canActivate: [AdminGuard] },
            // xử lý về bác sỹ
            { path: 'create-new-doctor', component: CreateDoctorComponent, canActivate: [AdminGuard] }, 
            { path: 'list-doctors', component: DoctorListComponent, canActivate: [AdminGuard] },
            // xử lý về bệnh nhân
            { path: 'list-patient', component: ListPatientComponent, canActivate: [AdminGuard] },
            // xử lý về dịch vụ và chuyên ngành
            { path: 'add-new-specialist', component: AddNewSpecialistComponent, canActivate: [AdminGuard] },
            { path: 'list-specialist', component: ListSpecialistsComponent, canActivate: [AdminGuard] },
            // xử lý về phòng
            { path: 'list-rooms', component: ListRoomComponent, canActivate: [AdminGuard] },
            // xử lý về vật tư
            { path: 'add-new-supply', component: AddNewSupplyComponent, canActivate: [AdminGuard] },
            { path: 'list-supplies', component: ListSuppliesComponent, canActivate: [AdminGuard] },
            // xử lý về thuốc
            { path: 'list-medication', component: ListMedicationComponent, canActivate: [AdminGuard] },
            // xử lý ca làm việc
            { path: 'list-shift', component: ListShiftComponent, canActivate: [AdminGuard] },
            { path: 'registry-schedule', component: RegistryScheduleManagementComponent, canActivate: [AdminGuard] },
        ]
    },

    // doctor
    {
        path: 'doctor', component: DoctorComponent,
        children: [
            { path: 'login', component: DoctorLoginComponent },
            { path: 'dashboard', component: DoctorDashboardComponent },
            { path: 'work-schedule', component: DoctorScheduleComponent },
            { path: 'register-schedule', component: DoctorRegisterScheduleComponent },
            { path: 'list-patient', component: DoctorListPatientComponent },
            { path: 'diagnostic/:id', component: DoctorDiagnosticComponent },
            { path: 'list-test', component: DoctorTestResultComponent },
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
